using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;
using AutoMapper;
using CloudPublishing.Business.DTO.RestApi;
using CloudPublishing.Business.Results;
using CloudPublishing.Business.Results.Interfaces;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Business.Util;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Interfaces;
using Newtonsoft.Json.Linq;

namespace CloudPublishing.Business.Services
{
    public class EmployeeApiService : IEmployeeApiService
    {
        private readonly IUnitOfWork unitOfWork;
        private readonly IMapper mapper;

        public EmployeeApiService(IUnitOfWork unitOfWork)
        {
            this.unitOfWork = unitOfWork;
            mapper = new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeBusinessApiMapProfile())).CreateMapper();
        }

        public void Dispose()
        {
            unitOfWork?.Dispose();
        }

        public async Task<IResult<JournalistStatisticsDTO>> GetJournalistStatistics(int? id)
        {
            if (id == null)
            {
                return new BadResult<JournalistStatisticsDTO>("Отсутствует идентификатор сотрудника");
            }

            try
            {
                // TODO: обработать null
                var journalist = unitOfWork.Employees.Get(id.Value);
                var statistics = mapper.Map<Employee, JournalistStatisticsDTO>(journalist);
                statistics.ArticleCount = await unitOfWork.Articles.GetJournalistArticleCount(id.Value);
                statistics.PublishingArticles =
                    await unitOfWork.Articles.GetJournalistArticleCountByPublishings(id.Value);
                statistics.TopicArticles = await unitOfWork.Articles.GetJournalistArticleCountByTopics(id.Value);
                return new SuccessfulResult<JournalistStatisticsDTO>(statistics);
            }
            catch (InvalidOperationException e)
            {
                return new BadResult<JournalistStatisticsDTO>(e);
            }
            catch (HttpRequestException e)
            {
                return new BadResult<JournalistStatisticsDTO>(e);
            }

        }

        public async Task<IResult<IEnumerable<JournalistDTO>>> GetJournalistList(JournalistListFilterDTO filter)
        {
            if (filter == null)
            {
                return new BadResult<IEnumerable<JournalistDTO>>("Отсутствует фильтр");
            }

            try
            {
                var journalistsInfo = await unitOfWork.Articles.GetJournalistFilteredList(filter.PublishingId,
                    filter.IssueId, filter.TopicId, filter.ArticleTitle);
                var journalist = mapper.Map<IEnumerable<Employee>, List<JournalistDTO>>(unitOfWork.Employees.Find(x => journalistsInfo.ContainsKey(x.Id)));
                
                return await Task.FromResult(new SuccessfulResult<IEnumerable<JournalistDTO>>(journalist.Select(x =>
                {
                    x.ArticlesCount = journalistsInfo[x.Id];
                    return x;
                })));
            }
            catch (InvalidOperationException e)
            {
                return new BadResult<IEnumerable<JournalistDTO>>(e);
            }
            catch (HttpRequestException e)
            {
                return new BadResult<IEnumerable<JournalistDTO>>(e);
            }
        }
    }
}
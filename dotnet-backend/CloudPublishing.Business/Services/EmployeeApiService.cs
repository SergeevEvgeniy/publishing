using System;
using System.Collections.Generic;
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
        private readonly HttpClient client;
        private readonly IUnitOfWork unitOfWork;
        private readonly IMapper mapper;

        public EmployeeApiService(IUnitOfWork unitOfWork)
        {
            this.unitOfWork = unitOfWork;
            client = new HttpClient();
            mapper = new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeBusinessApiMapProfile())).CreateMapper();
        }

        public void Dispose()
        {
            client?.Dispose();
        }

        public async Task<IResult<JournalistStatisticsDTO>> GetJournalistStatistics(int? id)
        {
            if (id == null)
            {
                return new BadResult<JournalistStatisticsDTO>("Отсутствует идентификатор сотрудника");
            }

            try
            {
                var journalist = unitOfWork.Employees.Get(id.Value);
                var statistics = mapper.Map<Employee, JournalistStatisticsDTO>(journalist);
                // TODO: Получать статистику
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

        public async Task<IResult<IEnumerable<JournalistListItemDTO>>> GetJournalistList(JournalistListFilterDTO filter)
        {
            if (filter == null)
            {
                return new BadResult<IEnumerable<JournalistListItemDTO>>("Отсутствует фильтр");
            }

            try
            {
                var result = new List<int>
                {
                    1,2
                };

                var journalists = unitOfWork.Employees.Find(x => result.Contains(x.Id));

                return new SuccessfulResult<IEnumerable<JournalistListItemDTO>>(mapper.Map<IEnumerable<Employee>, List<JournalistListItemDTO>>(journalists));
            }
            catch (InvalidOperationException e)
            {
                return new BadResult<IEnumerable<JournalistListItemDTO>>(e);
            }
            catch (HttpRequestException e)
            {
                return new BadResult<IEnumerable<JournalistListItemDTO>>(e);
            }
        }
    }
}
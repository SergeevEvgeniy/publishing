using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using CloudPublishing.Business.DTO.RestApi;
using CloudPublishing.Business.Results.Interfaces;

namespace CloudPublishing.Business.Services.Interfaces
{
    public interface IEmployeeApiService : IDisposable
    {
        Task<IResult<JournalistStatisticsDTO>> GetJournalistStatistics(int? id);

        Task<IResult<IEnumerable<JournalistListItemDTO>>> GetJournalistList(JournalistListFilterDTO filter);
    }
}
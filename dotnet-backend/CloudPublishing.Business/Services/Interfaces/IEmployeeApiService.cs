using System;
using System.Threading.Tasks;
using CloudPublishing.Business.Results.Interfaces;

namespace CloudPublishing.Business.Services.Interfaces
{
    public interface IEmployeeApiService : IDisposable
    {
        Task<IResult<int>> GetEmployeeArticleCount(int? id);
    }
}
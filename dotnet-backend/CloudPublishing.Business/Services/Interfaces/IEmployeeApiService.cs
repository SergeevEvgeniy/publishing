using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Results.Interfaces;

namespace CloudPublishing.Business.Services.Interfaces
{
    public interface IEmployeeApiService : IDisposable
    {
        IResult<IEnumerable<EmployeeDTO>> GetEmployeeInformation(List<int> employeeIdList);
    }
}
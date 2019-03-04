using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;
using AutoMapper;
using CloudPublishing.Business.DTO;
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
        private readonly IUnitOfWork unit;
        private readonly IMapper mapper;

        public EmployeeApiService(IUnitOfWork unit)
        {
            this.unit = unit;
            mapper = new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeBusinessMapProfile())).CreateMapper();
        }

        public void Dispose()
        {
            unit?.Dispose();
        }

        public IResult<IEnumerable<EmployeeDTO>> GetEmployeeInformation(List<int> employeeIdList)
        {
            try
            {
                var list = unit.Employees.Find(x => employeeIdList.Contains(x.Id));
                return new SuccessfulResult<IEnumerable<EmployeeDTO>>(mapper.Map<IEnumerable<Employee>, List<EmployeeDTO>>(list));
            }
            catch (InvalidOperationException e)
            {
                return new BadResult<IEnumerable<EmployeeDTO>>(e);
            }
        }
    }
}
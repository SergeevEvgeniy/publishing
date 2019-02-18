using System.Collections.Generic;
using System.Data.Common;
using System.Threading.Tasks;
using AutoMapper;
using CloudPublishing.Models.Employees.DTO;
using CloudPublishing.Models.Employees.Entities;
using CloudPublishing.Models.Employees.Repositories.Interfaces;
using CloudPublishing.Models.Employees.Results;
using CloudPublishing.Models.Employees.Results.Interfaces;
using CloudPublishing.Models.Employees.Services.Interfaces;
using CloudPublishing.Models.Employees.Util;

namespace CloudPublishing.Models.Employees.Services
{
    public class EmployeeService : IEmployeeService
    {
        private readonly IEmployeeRepository repository;

        public EmployeeService(IEmployeeRepository repository)
        {
            this.repository = repository;
        }

        public void Dispose()
        {
            repository?.Dispose();
        }

        public async Task<IResult<IEnumerable<EmployeeDTO>>> GetEmployeeList()
        {
            try
            {
                var list = await Task.Run(() => new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper()
                    .Map<IEnumerable<Employee>, List<EmployeeDTO>>(
                        repository.GetEmployeeList()));
                return new SuccessfulResult<IEnumerable<EmployeeDTO>>(list);
            }
            catch (DbException e)
            {
                return new BadResult<IEnumerable<EmployeeDTO>>(e);
            }
        }

        public async Task<IResult<IEnumerable<EducationDTO>>> GetEducationList()
        {
            try
            {
                var list = await Task.Run(() => new MapperConfiguration(cfg => cfg.CreateMap<Education, EducationDTO>())
                    .CreateMapper()
                    .Map<IEnumerable<Education>, List<EducationDTO>>(repository.GetEducationList()));
                return new SuccessfulResult<IEnumerable<EducationDTO>>(list);
            }
            catch (DbException e)
            {
                return new BadResult<IEnumerable<EducationDTO>>(e);
            }
        }

        public async Task<IResult<EmployeeDTO>> GetEmployeeById(int id)
        {
            try
            {
                var employee = await Task.Run(() =>
                    new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper()
                        .Map<Employee, EmployeeDTO>(repository.Find(id)));
                return new SuccessfulResult<EmployeeDTO>(employee);
            }
            catch (DbException e)
            {
                return new BadResult<EmployeeDTO>(e);
            }
        }
    }
}
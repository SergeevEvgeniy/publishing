using System.Collections.Generic;
using System.Threading.Tasks;
using AutoMapper;
using CloudPublishing.Models.Employees.DTO;
using CloudPublishing.Models.Employees.EF.Interfaces;
using CloudPublishing.Models.Employees.Entities;
using CloudPublishing.Models.Employees.Services.Interfaces;
using CloudPublishing.Models.Employees.Util;

namespace CloudPublishing.Models.Employees.Services
{
    public class EmployeeService : IEmployeeService
    {
        private readonly IUnitOfWork unitOfWork;

        public EmployeeService(IUnitOfWork unitOfWork)
        {
            this.unitOfWork = unitOfWork;
        }

        public void Dispose()
        {
            unitOfWork?.Dispose();
        }

        public async Task<IEnumerable<EmployeeDTO>> GetEmployeeList()
        {
            return new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper()
                .Map<IEnumerable<Employee>, List<EmployeeDTO>>(
                    await unitOfWork.Employees.FindAllAsync(x => true));
        }

        public async Task<IEnumerable<EducationDTO>> GetEducationList()
        {
            var mapper = new MapperConfiguration(cfg => cfg.CreateMap<Education, EducationDTO>()).CreateMapper();
            return mapper.Map<IEnumerable<Education>, List<EducationDTO>>(
                await unitOfWork.Education.FindAllAsync(x => true));
        }

        public async Task<EmployeeDTO> CreateEmployee(EmployeeDTO entity)
        {
            var employee = await unitOfWork.Employees.Create(
                new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile()))
                    .CreateMapper().Map<EmployeeDTO, Employee>(entity));
            entity.Id = employee.Id;
            return entity;
        }

        public async Task<EmployeeDTO> GetEmployeeById(int id)
        {
            return new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper()
                .Map<Employee, EmployeeDTO>(await unitOfWork.Employees.FindAsync(id));
        }

        public async Task EditEmployee(EmployeeDTO entity)
        {
            await unitOfWork.Employees.Update(
                new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile()))
                    .CreateMapper().Map<EmployeeDTO, Employee>(entity));
        }
    }
}
using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Business.Util;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;

namespace CloudPublishing.Business.Services
{
    public class EmployeeService : IEmployeeService
    {
        private readonly IMapper mapper;
        private readonly IUnitOfWork unitOfWork;

        public EmployeeService(IUnitOfWork unitOfWork)
        {
            this.unitOfWork = unitOfWork;
            mapper = new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeBusinessMapProfile())).CreateMapper();
        }

        public void Dispose()
        {
            unitOfWork?.Dispose();
        }

        public IEnumerable<EmployeeDTO> GetEmployeeList()
        {
            return mapper.Map<IEnumerable<Employee>, List<EmployeeDTO>>(unitOfWork.Employees.GetAll());
        }

        public IEnumerable<EmployeeDTO> GetEmployeeList(IEnumerable<int> idList, string lastName)
        {
            var list = unitOfWork.Employees.Find(x => x.LastName.Contains(lastName ?? string.Empty) && idList.Contains(x.Id));
            return mapper.Map<IEnumerable<Employee>, List<EmployeeDTO>>(list);
        }

        public IEnumerable<EducationDTO> GetEducationList()
        {
            return mapper.Map<IEnumerable<Education>, List<EducationDTO>>(unitOfWork.Employees.GetEducationList());
        }

        public EmployeeDTO GetEmployeeById(int? id)
        {
            if (id == null)
            {
                return null;
            }
            return mapper.Map<Employee, EmployeeDTO>(unitOfWork.Employees.Get(id.Value));
        }
    }
}
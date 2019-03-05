using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Claims;
using System.Threading.Tasks;
using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Results;
using CloudPublishing.Business.Results.Interfaces;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Business.Util;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Identity.Entities;
using CloudPublishing.Data.Interfaces;
using Microsoft.AspNet.Identity;

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

        public IResult<IEnumerable<EmployeeDTO>> GetEmployeeList()
        {
            try
            {
                var list = mapper.Map<IEnumerable<Employee>, List<EmployeeDTO>>(unitOfWork.Employees.GetAll());
                return new SuccessfulResult<IEnumerable<EmployeeDTO>>(list);
            }
            catch (InvalidOperationException e)
            {
                return new BadResult<IEnumerable<EmployeeDTO>>(e);
            }
        }

        public IResult<IEnumerable<EducationDTO>> GetEducationList()
        {
            try
            {
                var list = mapper.Map<IEnumerable<Education>, List<EducationDTO>>(
                    unitOfWork.Employees.GetEducationList());
                return new SuccessfulResult<IEnumerable<EducationDTO>>(list);
            }
            catch (InvalidOperationException e)
            {
                return new BadResult<IEnumerable<EducationDTO>>(e);
            }
        }

        public IResult<EmployeeDTO> GetEmployeeById(int? id)
        {
            if (id == null) return new BadResult<EmployeeDTO>("Отсутствует идентификатор");
            try
            {
                var employee = mapper.Map<Employee, EmployeeDTO>(unitOfWork.Employees.Get(id.Value));
                return new SuccessfulResult<EmployeeDTO>(employee);
            }
            catch (InvalidOperationException e)
            {
                return new BadResult<EmployeeDTO>(e);
            }
        }
    }
}
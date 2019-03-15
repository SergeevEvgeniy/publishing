using System;
using System.Collections.Generic;
using System.Linq;
using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Business.Util;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Interfaces;
using CloudPublishing.Models.Employees.Enums;

namespace CloudPublishing.Business.Services
{
    public class EmployeeService : IEmployeeService
    {
        private static readonly IDictionary<EmployeeType, string> Types;
        private static readonly IDictionary<Sex, string> Sexes;
        private readonly IMapper mapper;
        private readonly IUnitOfWork unitOfWork;

        static EmployeeService()
        {
            Types = new Dictionary<EmployeeType, string>
            {
                {EmployeeType.E, "Редактор"},
                {EmployeeType.J, "Журналист"}
            };
            Sexes = new Dictionary<Sex, string>
            {
                {Sex.M, "М"},
                {Sex.F, "Ж"}
            };
        }

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
            return mapper.Map<IEnumerable<Employee>, List<EmployeeDTO>>(unitOfWork.Employees.GetAll().Select(x =>
            {
                x.Type = Types[(EmployeeType) Enum.Parse(typeof(EmployeeType), x.Type)];
                x.Sex = Sexes[(Sex) Enum.Parse(typeof(Sex), x.Sex)];
                return x;
            }));
        }

        public IEnumerable<EmployeeDTO> GetEmployeeList(IEnumerable<int> idList, string lastName)
        {
            var list = unitOfWork.Employees.Find(x =>
                x.LastName.Contains(lastName ?? string.Empty) && idList.Contains(x.Id));
            return mapper.Map<IEnumerable<Employee>, List<EmployeeDTO>>(list);
        }

        public IEnumerable<EducationDTO> GetEducationList()
        {
            return mapper.Map<IEnumerable<Education>, List<EducationDTO>>(unitOfWork.Employees.GetEducationList());
        }

        public EmployeeDTO GetEmployeeById(int? id)
        {
            return id == null ? null : mapper.Map<Employee, EmployeeDTO>(unitOfWork.Employees.Get(id.Value));
        }

        public IDictionary<string, string> GetEmployeeTypes()
        {
            return Types.Select(x => new {key = x.Key.ToString(), x.Value}).ToDictionary(x => x.key, y => y.Value);
        }
    }
}
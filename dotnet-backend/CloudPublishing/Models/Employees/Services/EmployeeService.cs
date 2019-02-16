﻿using System.Collections.Generic;
using System.Data.Common;
using System.Threading.Tasks;
using AutoMapper;
using CloudPublishing.Models.Employees.DTO;
using CloudPublishing.Models.Employees.EF.Interfaces;
using CloudPublishing.Models.Employees.Entities;
using CloudPublishing.Models.Employees.Results;
using CloudPublishing.Models.Employees.Results.Interfaces;
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

        public async Task<IResult<IEnumerable<EmployeeDTO>>> GetEmployeeList()
        {
            try
            {
                var list = await Task.Run(() => new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper()
                    .Map<IEnumerable<Employee>, List<EmployeeDTO>>(
                        unitOfWork.Employees.FindAll(x => true)));
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
                    .Map<IEnumerable<Education>, List<EducationDTO>>(unitOfWork.Education.FindAll(x => true)));
                return new SuccessfulResult<IEnumerable<EducationDTO>>(list);
            }
            catch (DbException e)
            {
                return new BadResult<IEnumerable<EducationDTO>>(e);
            }
        }

        public async Task<IResult<EmployeeDTO>> CreateEmployee(EmployeeDTO entity)
        {
            try
            {
                var employee = await Task.Run(() =>
                {
                    var target = new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper()
                        .Map<EmployeeDTO, Employee>(entity);
                    unitOfWork.Employees.Create(target);
                    unitOfWork.SaveAsync();
                    return new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper()
                        .Map<Employee, EmployeeDTO>(target);
                });
                return new SuccessfulResult<EmployeeDTO>(employee);
            }
            catch (DbException e)
            {
                return new BadResult<EmployeeDTO>(e);
            }
        }

        public async Task<IResult<EmployeeDTO>> GetEmployeeById(int id)
        {
            try
            {
                var employee = await Task.Run(() =>
                    new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper()
                        .Map<Employee, EmployeeDTO>(unitOfWork.Employees.Find(id)));
                return new SuccessfulResult<EmployeeDTO>(employee);
            }
            catch (DbException e)
            {
                return new BadResult<EmployeeDTO>(e);
            }
        }

        public async Task<IResult<int>> EditEmployee(EmployeeDTO entity)
        {
            try
            {
                var rows = await Task.Run(() =>
                {
                    unitOfWork.Employees.Update(
                        new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile()))
                            .CreateMapper().Map<EmployeeDTO, Employee>(entity));
                    return unitOfWork.SaveAsync();
                });
                return new SuccessfulResult<int>(rows);
            }
            catch (DbException e)
            {
                return new BadResult<int>(e);
            }
        }

        public async Task<IResult<int>> DeleteEmployee(int id)
        {
            try
            {
                var rows = await Task.Run(() =>
                {
                    unitOfWork.Employees.Delete(id);
                    return unitOfWork.SaveAsync();
                });
                return new SuccessfulResult<int>(rows);
            }
            catch (DbException e)
            {
                return new BadResult<int>(e);
            }
        }
    }
}
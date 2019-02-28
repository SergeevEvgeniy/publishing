﻿using AutoMapper;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Identity.Entities;

namespace CloudPublishing.Data.Identity
{
    public class EmployeeUserMapProfile : Profile
    {
        public EmployeeUserMapProfile()
        {
            CreateMap<EmployeeUser, Employee>()
                .ForMember(dest => dest.Email, opt => opt.MapFrom(src => src.UserName))
                .ForMember(dest => dest.Password, opt => opt.MapFrom(src => src.PasswordHash));

            CreateMap<Employee, EmployeeUser>()
                .ForMember(dest => dest.UserName, opt => opt.MapFrom(src => src.Email))
                .ForMember(dest => dest.PasswordHash, opt => opt.Ignore())
                .ForMember(dest => dest.Password, opt => opt.Ignore());
        }
    }
}
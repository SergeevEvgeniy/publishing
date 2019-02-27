using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;
using AutoMapper;
using CloudPublishing.Models.Accounts;
using CloudPublishing.Models.Accounts.Identity;
using CloudPublishing.Models.Employees.DTO;
using CloudPublishing.Models.Employees.Util;
using Microsoft.AspNet.Identity.Owin;
using Newtonsoft.Json.Linq;

namespace CloudPublishing.Controllers.RestApi
{
    [RoutePrefix("api/accounts")]
    public class AccountsController : ApiController
    {
        private EmployeeManager Manager => Request.GetOwinContext().GetUserManager<EmployeeManager>();

        [Route("users")]
        public IHttpActionResult GetUsers()
        {
            // TODO: создать сервис принимающий EmployeeManager и реализующий весь необходимый функционал
            var data = new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper()
                .Map<IEnumerable<EmployeeUser>, List<EmployeeDTO>>(Manager.Users.ToList());
            return Json(data);
        }

        [Route("user/{id:int}", Name = "GetUserById")]
        public async Task<IHttpActionResult> GetUser(int id)
        {
            var user = await Manager.FindByIdAsync(id);

            if (user != null)
            {
                return Json(new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper().Map<EmployeeUser, EmployeeDTO>(user));
            }

            return BadRequest();
        }

        [Route("user/{id}", Name = "GetUserByName")]
        public async Task<IHttpActionResult> GetUser(string name)
        {
            var user = await Manager.FindByNameAsync(name);

            if (user != null)
            {
                return Json(new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper().Map<EmployeeUser, EmployeeDTO>(user));
            }

            return BadRequest();
        }

        [Route("create")]
        public async Task<IHttpActionResult> CreateUser(EmployeeUserCreateModel model)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var user = new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper()
                .Map<EmployeeUserCreateModel, EmployeeUser>(model);

            var result = await Manager.CreateAsync(user, model.Password);

            if (!result.Succeeded)
            {
                return InternalServerError();
            }

            var locationHeader = new Uri(Url.Link("GetUserById", new { id = user.Id }));

            return Created(locationHeader,
                new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper()
                    .Map<EmployeeUser, EmployeeDTO>(user));
        }
    }
}

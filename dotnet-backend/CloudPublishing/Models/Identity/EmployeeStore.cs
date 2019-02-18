using System;
using System.Data.Entity;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using CloudPublishing.Models.Employees.EF;
using CloudPublishing.Models.Employees.Entities;
using CloudPublishing.Models.Employees.Util;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.EntityFramework;

namespace CloudPublishing.Models.Identity
{
    public class EmployeeStore : IUserPasswordStore<EmployeeIdentity, int>
    {
        private readonly EmployeeContext context;

        public EmployeeStore(EmployeeContext context)
        {
            this.context = context;
        }

        public void Dispose()
        {
            context?.Dispose();
        }

        public async Task CreateAsync(EmployeeIdentity user)
        {
            var employee = new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper()
                .Map<EmployeeIdentity, Employee>(user);
            context.Employees.Add(employee);
            await context.SaveChangesAsync();
        }

        public async Task UpdateAsync(EmployeeIdentity user)
        {
            var employee = new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper()
                .Map<EmployeeIdentity, Employee>(user);
            if (user.ChiefEditor)
            {
                var chiefEditor = await context.Employees.FirstOrDefaultAsync(x => x.ChiefEditor);
                if (chiefEditor != null) chiefEditor.ChiefEditor = false;
            }

            context.Entry(employee).State = EntityState.Modified;
            await context.SaveChangesAsync();
        }

        public Task DeleteAsync(EmployeeIdentity user)
        {
            var employee = new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper()
                .Map<EmployeeIdentity, Employee>(user);
            context.Entry(employee).State = EntityState.Deleted;
            return context.SaveChangesAsync();
        }

        public Task<EmployeeIdentity> FindByIdAsync(int userId)
        {
            return Task.Run(() =>
            {
                var employee = context.Employees.Find(userId);
                return new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper()
                    .Map<Employee, EmployeeIdentity>(employee);
            });
        }

        public Task<EmployeeIdentity> FindByNameAsync(string userName)
        {
            return Task.Run(() =>
            {
                var employee = context.Employees.FirstOrDefault(x => x.Email == userName);
                return new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper()
                    .Map<Employee, EmployeeIdentity>(employee);
            });
        }

        public Task SetPasswordHashAsync(EmployeeIdentity user, string passwordHash)
        {
            return Task.Run(() => user.PasswordHash = passwordHash);
        }

        public Task<string> GetPasswordHashAsync(EmployeeIdentity user)
        {
            return Task.Run(() => user.PasswordHash);
        }

        public Task<bool> HasPasswordAsync(EmployeeIdentity user)
        {
            return Task.Run(() => user.Password != null);
        }
    }
}
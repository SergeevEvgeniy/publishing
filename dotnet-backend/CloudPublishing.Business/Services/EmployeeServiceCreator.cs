using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Data.Interfaces;
using CloudPublishing.Data.Repositories;

namespace CloudPublishing.Business.Services
{
    public class EmployeeServiceCreator : IEmployeeServiceCreator
    {
        public IEmployeeService Create()
        {
            return new EmployeeService(new UnitOfWork("EmployeeContext"));
        }
    }
}
using System.Net.Http;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Data.Interfaces;

namespace CloudPublishing.Business.Services
{
    public class EmployeeApiService : IEmployeeApiService
    {
        private readonly HttpClient client;
        private readonly IUnitOfWork unitOfWork;

        public EmployeeApiService(IUnitOfWork unitOfWork)
        {
            this.unitOfWork = unitOfWork;
            client = new HttpClient();
        }

        public void Dispose()
        {
            client?.Dispose();
        }
    }
}
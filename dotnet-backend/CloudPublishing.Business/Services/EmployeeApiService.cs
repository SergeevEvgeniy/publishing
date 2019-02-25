using System.Net.Http;
using System.Threading.Tasks;
using CloudPublishing.Business.Results;
using CloudPublishing.Business.Results.Interfaces;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Data.Interfaces;
using Newtonsoft.Json.Linq;

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

        public async Task<IResult<int>> GetEmployeeArticleCount(int? id)
        {
            if (id == null)
            {
                return new BadResult<int>("Отсутствует идентификатор");
            }

            try
            {
                var response = await client.GetStringAsync("http://10.99.33.138:53130/api/publishing/" + id.Value);
                var result = JObject.Parse(response);
                return new SuccessfulResult<int>(0);
            }
            catch (HttpRequestException e)
            {
                return new BadResult<int>(e);
            }
            

        }
    }
}
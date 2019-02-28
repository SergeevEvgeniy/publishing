using CloudPublishing.Data.Interfaces;

namespace CloudPublishing.Business.Services.Interfaces
{
    public interface IEmployeeServiceCreator
    {
        IEmployeeService Create();
    }
}
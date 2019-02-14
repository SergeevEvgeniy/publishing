using System;

namespace CloudPublishing.Models.Employees.Results.Interfaces
{
    public interface IResult<T>
    {
        bool IsSuccessful { get; }

        T GetContent();

        string GetFailureMessage();
    }
}
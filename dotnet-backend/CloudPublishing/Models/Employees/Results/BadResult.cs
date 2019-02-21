using System;
using CloudPublishing.Models.Employees.Results.Interfaces;

namespace CloudPublishing.Models.Employees.Results
{
    public class BadResult<T> : IResult<T>
    {
        private readonly string message;

        public BadResult(Exception exception)
        {
            message = exception.Message;
        }

        public BadResult(string message)
        {
            this.message = message;
        }

        public bool IsSuccessful => false;

        public T GetContent()
        {
            return default(T);
        }

        public string GetFailureMessage()
        {
            return message;
        }
    }
}
using System;
using CloudPublishing.Business.Results.Interfaces;

namespace CloudPublishing.Business.Results
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
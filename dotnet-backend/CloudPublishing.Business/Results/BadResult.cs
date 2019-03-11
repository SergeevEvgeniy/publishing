using CloudPublishing.Business.Results.Interfaces;

namespace CloudPublishing.Business.Results
{
    public class BadResult<T> : IResult<T>
    {
        private readonly string message;

        public BadResult(string message)
        {
            this.message = message;
            IsExternalException = false;
        }

        public BadResult(string message, bool isExternalException)
        {
            this.message = message;
            this.IsExternalException = isExternalException;
        }

        public bool IsSuccessful => false;

        public bool IsExternalException { get; }

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
using CloudPublishing.Business.Results.Interfaces;

namespace CloudPublishing.Business.Results
{
    public class SuccessfulResult<T> : IResult<T>
    {
        private readonly T content;

        public SuccessfulResult(T content)
        {
            this.content = content;
        }

        public bool IsSuccessful => true;

        public T GetContent()
        {
            return content;
        }

        public string GetFailureMessage()
        {
            return string.Empty;
        }
    }
}
namespace CloudPublishing.Business.Results.Interfaces
{
    public interface IResult<T>
    {
        bool IsSuccessful { get; }

        T GetContent();

        string GetFailureMessage();
    }
}
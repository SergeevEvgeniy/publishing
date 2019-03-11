namespace CloudPublishing.Business.Results.Interfaces
{
    public interface IResult<out T>
    {
        bool IsSuccessful { get; }

        bool IsExternalException { get; }

        T GetContent();

        string GetFailureMessage();
    }
}
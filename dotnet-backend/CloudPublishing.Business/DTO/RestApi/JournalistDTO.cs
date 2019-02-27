namespace CloudPublishing.Business.DTO.RestApi
{
    public class JournalistDTO
    {
        public int Id { get; set; }

        public string FirstName { get; set; }

        public string LastName { get; set; }

        public string MiddleName { get; set; }

        public string EducationTitle { get; set; }

        public int ArticlesCount { get; set; }
    }
}
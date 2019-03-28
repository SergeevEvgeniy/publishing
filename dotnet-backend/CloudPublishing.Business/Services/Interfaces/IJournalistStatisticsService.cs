using CloudPublishing.Business.DTO;

namespace CloudPublishing.Business.Services.Interfaces
{
    public interface IJournalistStatisticsService
    {
        JournalistStatisticsDTO GetJournalistStatistics(int id);
    }
}
using System.Collections.Generic;

namespace CloudPublishing.Business.Constants
{
    public static class DataCorrelation
    {
        public static Dictionary<string, string> EmployeeTypes = new Dictionary<string, string>
        {
            {EmployeeType.Editor, "Редактор" },
            {EmployeeType.Journalist, "Журналист" }
        };

        public static Dictionary<string, string> EmployeeSexes = new Dictionary<string, string>
        {
            {EmployeeSex.Male, "М" },
            {EmployeeSex.Female, "Ж" }
        };

        public static Dictionary<string, string> PublishingTypes = new Dictionary<string, string>
        {
            {PublishingType.Magazine, "Журнал" },
            {PublishingType.Paper, "Газета" }
        };
    }
}
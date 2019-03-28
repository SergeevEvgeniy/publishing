using System.Collections.Generic;

namespace CloudPublishing.Business.Constants
{
    public class DataCorrelation
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
    }
}
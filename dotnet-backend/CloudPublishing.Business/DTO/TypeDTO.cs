using System;
using System.Collections.Generic;
using CloudPublishing.Business.Enums;

namespace CloudPublishing.Business.DTO
{
    public class TypeDTO
    {
        private static readonly IDictionary<EmployeeType, string> Data;

        private string id;

        static TypeDTO()
        {
            Data = new Dictionary<EmployeeType, string>
            {
                {EmployeeType.E, "Редактор"},
                {EmployeeType.J, "Журналист"}
            };
        }

        public string Id
        {
            get => id;
            set
            {
                if (!Enum.TryParse(value, out EmployeeType type))
                {
                    throw new FormatException("Не удалось создать объект типа сотрудника");
                }

                id = type.ToString();
                Title = Data[type];
            }
        }

        public string Title { get; private set; }
    }
}
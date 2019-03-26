using System;
using System.Collections.Generic;
using CloudPublishing.Business.Enums;

namespace CloudPublishing.Business.DTO
{
    public class SexDTO
    {
        private static readonly IDictionary<Sex, string> Data;

        private string id;

        static SexDTO()
        {
            Data = new Dictionary<Sex, string>
            {
                {Sex.M, "М"},
                {Sex.F, "Ж"}
            };
        }

        public string Id
        {
            get => id;
            set
            {
                if (!Enum.TryParse(value, out Sex type))
                {
                    throw new FormatException("Не удалось создать объект пола сотрудника");
                }

                id = type.ToString();
                Title = Data[type];
            }
        }

        public string Title { get; private set; }
    }
}
using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Models.Publishings.ViewModels;

namespace CloudPublishing.Util.PublishingValueResolvers
{
    public class EmployeeShortName : IValueResolver<EmployeeDTO, PublishingEmployeeViewModel, string>
    {
        public string Resolve(EmployeeDTO source, PublishingEmployeeViewModel destination, string destMember, ResolutionContext context)
        {
            string shortName = source.LastName + ' ' + source.FirstName[0] + '.';

            if (source.MiddleName != null)
            {
                shortName = shortName + source.MiddleName[0] + '.';
            }

            return shortName;
        }
    }
}
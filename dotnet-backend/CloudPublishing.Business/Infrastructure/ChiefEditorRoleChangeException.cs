using System;

namespace CloudPublishing.Business.Infrastructure
{
    public class ChiefEditorRoleChangeException : ApplicationException
    {
        public ChiefEditorRoleChangeException() : base("Сначала необходимо указать другого главного редактора")
        {
            
        }
    }
}
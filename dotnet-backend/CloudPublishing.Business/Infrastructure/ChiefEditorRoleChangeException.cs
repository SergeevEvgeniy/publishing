using System;

namespace CloudPublishing.Business.Infrastructure
{
    public class ChiefEditorRoleChangeException : Exception
    {
        public ChiefEditorRoleChangeException() : base("Сначала необходимо указать другого главного редактора")
        {
            
        }
    }
}
using System;

namespace CloudPublishing.Business.Infrastructure
{
    public class ChiefEditorExistenceException : Exception
    {
        public ChiefEditorExistenceException() : base("Сначала необходимо указать другого главного редактора")
        {
            
        }
    }
}
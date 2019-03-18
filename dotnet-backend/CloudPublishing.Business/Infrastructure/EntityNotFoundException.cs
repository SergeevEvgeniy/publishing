using System;

namespace CloudPublishing.Business.Infrastructure
{
    public class EntityNotFoundException : ApplicationException
    {
        public EntityNotFoundException(string message): base(message)
        {
            
        }
    }
}
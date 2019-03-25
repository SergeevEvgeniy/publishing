namespace CloudPublishing.Models.Employees.ApiModels
{
    public class EmployeeData
    {
        public int Id { get; set; }

        public string FirstName { get; set; }

        public string LastName { get; set; }

        public string MiddleName { get; set; }

        public string Email { get; set; }

        public string Sex { get; set; }

        public short BirthYear { get; set; }

        public string Address { get; set; }

        public string Type { get; set; }

        public bool ChiefEditor { get; set; }

        public string Education { get; set; }
    }
}
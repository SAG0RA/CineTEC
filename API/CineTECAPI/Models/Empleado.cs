using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace CineTECAPI.Models
{
    public class Empleado
    {
        public int cedula { get; set; }
        public string nombre { get; set; }
        public string rol { get; set; }
        public string usuario { get; set; }
        public string contraseña { get; set; }

    }
}

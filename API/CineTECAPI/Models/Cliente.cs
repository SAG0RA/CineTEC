using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace CineTECAPI.Models
{
    public class Cliente
    {
        public int cedula { get; set; }
        public string pnombre { get; set; }
        public string snombre { get; set; }
        public string apellido { get; set; }
        public int telefono { get; set; }
        public DateTime fechanac { get; set; }
        public int edad { get; set; }

    }
}

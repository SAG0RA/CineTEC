using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace CineTECAPI.Models
{
    public class Pelicula
    {
        public string nombreog { get; set; }
        public string nombre { get; set; }
        public string imagen { get; set; }
        public string duracion { get; set; }
        public string protagonistas { get; set; }
        public string director { get; set; }
        public string clasificacion { get; set; }
        public string[] proyecciones { get; set; }

    }
}

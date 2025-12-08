package utils;

public class LimitsContentProvider {

        // --- TEORÍA ---
        public static String getTheoryTitle(LimitsContext.Topic topic) {
                switch (topic) {
                        case DEFINICION:
                                return "Definición de Límite";
                        case LATERALES:
                                return "Límites Laterales";
                        case PROPIEDADES:
                                return "Propiedades de los Límites";
                        default:
                                return "";
                }
        }

        public static String getTheoryContent(LimitsContext.Topic topic) {
                switch (topic) {
                        case DEFINICION:
                                return "1. Introducción al Concepto de Límite\n" +
                                                "El cálculo diferencial se basa fundamentalmente en el concepto de límite. A diferencia del álgebra, donde evaluamos expresiones en valores exactos, en cálculo nos interesa saber qué sucede con una función cuando nos 'acercamos' arbitrariamente a un valor, sin necesariamente llegar a él.\n\n"
                                                +
                                                "2. Definición Intuitiva\n" +
                                                "El límite de una función f(x) cuando x se aproxima a un valor 'a', es el número L al cual se acercan los valores de f(x) cuando x se acerca a 'a' (por ambos lados).\n\n"
                                                +
                                                "Notación Matemática:\nlim(x→a) f(x) = L\n\n" +
                                                "Esto se lee: 'El límite de f de x cuando x tiende a a es igual a L'.\n\n"
                                                +
                                                "3. ¿Qué significa 'tender'?\n" +
                                                "Significa tomar valores de x cada vez más cercanos a 'a', pero sin tomar el valor x = a. Por ejemplo, si x tiende a 3, x puede valer 2.9, 2.99, 2.999... o 3.1, 3.01, 3.001.\n\n"
                                                +
                                                "4. Existencia del Límite\n" +
                                                "Para que el límite exista igual a L, f(x) debe acercarse a L tanto si x se acerca a 'a' por valores menores (izquierda) como por valores mayores (derecha). No importa si f(a) está definida o no; el límite depende solo del comportamiento VECINO al punto.";
                        case LATERALES:
                                return "1. Acercamiento por dos caminos\n" +
                                                "Como vimos, x puede acercarse a 'a' desde dos direcciones. Esto da lugar a los Límites Laterales.\n\n"
                                                +
                                                "2. Límite Lateral por la Derecha (x → a⁺)\n" +
                                                "Ocurre cuando x toma valores mayores que 'a'.\n" +
                                                "Notación: lim(x→a⁺) f(x) = L₁\n" +
                                                "Ejemplo: Si x → 2⁺, usamos x = 2.1, 2.01, 2.001.\n\n" +
                                                "3. Límite Lateral por la Izquierda (x → a⁻)\n" +
                                                "Ocurre cuando x toma valores menores que 'a'.\n" +
                                                "Notación: lim(x→a⁻) f(x) = L₂\n" +
                                                "Ejemplo: Si x → 2⁻, usamos x = 1.9, 1.99, 1.999.\n\n" +
                                                "4. Teorema de Existencia\n" +
                                                "El límite general (bilateral) lim(x→a) f(x) EXISTE si y solo si los dos límites laterales existen y son IGUALES.\n"
                                                +
                                                "Si lim(x→a⁻) f(x) = lim(x→a⁺) f(x) = L, entonces lim(x→a) f(x) = L.\n"
                                                +
                                                "Si son diferentes, el límite NO existe.";
                        case PROPIEDADES:
                                return "1. Propiedades Algebraicas\n" +
                                                "Para facilitar el cálculo de límites sin usar tablas de valores, usamos propiedades fundamentales. Supongamos que lim(x→a) f(x) = L y lim(x→a) g(x) = M.\n\n"
                                                +
                                                "• Límite de una Constante: lim(x→a) k = k\n" +
                                                "• Límite de la variable x: lim(x→a) x = a\n\n" +
                                                "• Regla de la Suma/Resta:\n" +
                                                "lim [f(x) ± g(x)] = L ± M\n" +
                                                "(El límite de una suma es la suma de los límites)\n\n" +
                                                "• Regla del Producto:\n" +
                                                "lim [f(x) · g(x)] = L · M\n\n" +
                                                "• Regla del Cociente:\n" +
                                                "lim [f(x) / g(x)] = L / M, siempre que M ≠ 0.\n" +
                                                "Si M = 0, tenemos un caso especial o indeterminación.\n\n" +
                                                "• Regla de la Potencia/Raíz:\n" +
                                                "lim [f(x)]ⁿ = Lⁿ\n" +
                                                "lim ⁿ√f(x) = ⁿ√L (si n es par, L debe ser > 0).";
                        default:
                                return "";
                }
        }

        // --- PREGUNTAS DE TEORÍA ---
        // Returns: {Pregunta, Opción1, Opción2, Opción3, IndiceCorrecto, Puntos}
        public static String[][] getTheoryQuestions(LimitsContext.Topic topic) {
                switch (topic) {
                        case DEFINICION: // Total 5 pts (1 cada uno)
                                return new String[][] {
                                                { "El límite L es el valor al que se acerca f(x) cuando x se acerca a:",
                                                                "Infinito", "L", "a", "2", "1" },
                                                { "¿Es necesario que f(a) exista para que exista el límite cuando x->a?",
                                                                "Sí, siempre", "No, no es necesario",
                                                                "Solo en funciones continuas", "1", "1" },
                                                { "Si nos acercamos a 'a' por valores mayores, estamos hablando de:",
                                                                "Límite lateral izquierdo", "Límite lateral derecho",
                                                                "Límite central", "1", "1" },
                                                { "El concepto de límite es fundamental para:", "El álgebra básica",
                                                                "El cálculo diferencial", "La geometría plana", "1",
                                                                "1" },
                                                { "La notación lim(x→a) se lee:", "Límite de f(x) cuando x tiende a a",
                                                                "f(x) dividido por x-a", "x aproximado a a", "0", "1" }
                                };
                        case LATERALES: // Total 5 pts (1 cada uno)
                                return new String[][] {
                                                { "El límite lateral por la izquierda se denota con:", "x -> a⁺",
                                                                "x -> a⁻", "x -> -a", "1", "1" },
                                                { "Si lim(x->a⁻) != lim(x->a⁺), entonces:", "El límite existe",
                                                                "El límite es infinito", "El límite no existe", "2",
                                                                "1" },
                                                { "Para analizar el comportamiento cerca de un punto, usamos:",
                                                                "Derivadas", "Integrales", "Límites Laterales", "2",
                                                                "1" },
                                                { "Si lim(x->a⁻) = 5 y lim(x->a⁺) = 5, el límite es:", "5", "10",
                                                                "No existe", "0", "1" },
                                                { "Los límites laterales evalúan la función en:", "El punto exacto",
                                                                "Valores muy cercanos", "El infinito", "1", "1" }
                                };
                        case PROPIEDADES: // Total 5 pts (1 cada uno)
                                return new String[][] {
                                                { "El límite de una suma es:", "La suma de los límites",
                                                                "El producto de los límites", "Cero", "0", "1" },
                                                { "El límite de una constante k es:", "0", "k", "Infinito", "1", "1" },
                                                { "El límite de un producto es:", "La suma de límites",
                                                                "El producto de límites", "Uno", "1", "1" },
                                                { "Para el límite del cociente, el denominador debe ser:", "Cero",
                                                                "Diferente de cero", "Uno", "1", "1" },
                                                { "Si el límite existe, este es:", "Único", "Múltiple", "Variable", "0",
                                                                "1" }
                                };
                        default:
                                return new String[][] {};
                }
        }

        // --- VIDEO ---
        public static String[] getVideoData(LimitsContext.Topic topic) {
                switch (topic) {
                        case DEFINICION:
                                return new String[] { "https://www.youtube.com/watch?v=e1dQTxTlmzE",
                                                "Definición de Límite",
                                                "Conceptos fundamentales para entender el cálculo." };
                        case LATERALES:
                                return new String[] { "https://youtu.be/-Izg1gMA3-I", "Límites Laterales",
                                                "Aproximación por izquierda y derecha: clave para la existencia del límite." };
                        case PROPIEDADES:
                                return new String[] { "https://youtu.be/PYBYCP6aeiI", "Propiedades de los Límites",
                                                "Reglas operativas para calcular límites sin tablas." };
                        default:
                                return new String[] { "", "", "" };
                }
        }

        // --- QUIZ (CUESTIONARIO) ---
        // Returns: {Pregunta, Opción1, Opción2, Opción3, IndiceCorrecto, Puntos}
        public static String[][] getQuizQuestions(LimitsContext.Topic topic) {
                switch (topic) {
                        case DEFINICION: // Total 10 pts (2 cada uno)
                                return new String[][] {
                                                { "Si x se acerca a 2, x puede tomar el valor:", "1.999", "2", "3", "0",
                                                                "2" },
                                                { "En la expresión lim(x->3) 2x, el resultado es:", "3", "5", "6", "2",
                                                                "2" },
                                                { "El límite describe el comportamiento de la función:",
                                                                "En el infinito", "Alrededor de un punto",
                                                                "Solo en el origen", "1", "2" },
                                                { "Si f(x) = x+1, lim(x->1) f(x) es:", "0", "1", "2", "2", "2" },
                                                { "¿El límite evalúa lo que pasa EXACTAMENTE en x=a?", "Sí", "No",
                                                                "A veces", "1", "2" }
                                };
                        case LATERALES: // Total 10 pts (2 cada uno)
                                return new String[][] {
                                                { "Si lim(x->2⁻) = 3 y lim(x->2⁺) = 4, el límite:", "Es 3.5", "Es 7",
                                                                "No existe", "2", "2" },
                                                { "Dada f(x) = |x|/x, lim(x->0⁺) es:", "1", "-1", "0", "0", "2" },
                                                { "Dada f(x) = |x|/x, lim(x->0⁻) es:", "1", "-1", "0", "1", "2" },
                                                { "En una función continua, los límites laterales son:", "Diferentes",
                                                                "Iguales", "Nan", "1", "2" },
                                                { "Si una función tiene un salto en x=a, el límite:", "Es infinito",
                                                                "Es cero", "No existe", "2", "2" }
                                };
                        case PROPIEDADES: // Total 10 pts (2 cada uno)
                                return new String[][] {
                                                { "lim(x->1) (3x + 2) =", "3", "5", "1", "1", "2" },
                                                { "lim(x->2) (x^2) =", "2", "4", "0", "1", "2" },
                                                { "lim(x->5) 10 =", "5", "10", "50", "1", "2" },
                                                { "lim(x->0) (x+1)(x-1) =", "0", "-1", "1", "1", "2" },
                                                { "Si lim f(x)=2 y lim g(x)=3, lim (f(x)+g(x)) =", "5", "6", "1", "0",
                                                                "2" }
                                };
                        default:
                                return new String[][] {};
                }
        }

        // --- EXERCISES (SERIES) ---
        // Returns: {Título, Problema, Solución}
        public static String[][] getExercises(LimitsContext.Topic topic) {
                switch (topic) {
                        case DEFINICION:
                                return new String[][] {
                                                { "Ejercicio 1", "Evaluar lim(x→2) (3x - 1)",
                                                                "Paso 1: Identificar x→2\nPaso 2: Sustituir x por 2\nPaso 3: 3(2) - 1 = 6 - 1 = 5" },
                                                { "Ejercicio 2", "Evaluar lim(x→-1) (x² + 2)",
                                                                "Paso 1: Identificar x→-1\nPaso 2: Sustituir x por -1\nPaso 3: (-1)² + 2 = 1 + 2 = 3" }
                                };
                        case LATERALES:
                                return new String[][] {
                                                { "Ejercicio 1", "Dada f(x) = { 2x si x<1, x+1 si x>1 }. Hallar lim(x→1)",
                                                                "Paso 1: Lateral Izq (x<1) -> 2(1) = 2\nPaso 2: Lateral Der (x>1) -> 1+1 = 2\nPaso 3: Como 2=2, el límite existe y es 2." },
                                                { "Ejercicio 2", "Dada f(x) = { 1 si x<0, -1 si x>0 }. Hallar lim(x→0)",
                                                                "Paso 1: Lateral Izq (x<0) -> 1\nPaso 2: Lateral Der (x>0) -> -1\nPaso 3: Como 1 ≠ -1, el límite NO existe." }
                                };
                        case PROPIEDADES:
                                return new String[][] {
                                                { "Ejercicio 1", "lim(x→2) (x² + 3x)",
                                                                "Propiedad Suma: lim x² + lim 3x\n= 2² + 3(2) = 4 + 6 = 10" },
                                                { "Ejercicio 2", "lim(x→1) (x+1)/(x+2)",
                                                                "Propiedad Cociente:\nNumerator: 1+1 = 2\nDenominator: 1+2 = 3\nResultado: 2/3" }
                                };
                        default:
                                return new String[][] {};
                }
        }

        // --- PRÁCTICA ---
        // Returns: Array of Steps -> {Pregunta, Opción1, Opción2, Opción3, Opción4,
        // IndiceCorrecto, Puntos}
        public static String[][][] getPracticeSteps(LimitsContext.Topic topic) {
                switch (topic) {
                        case DEFINICION: // Needs 18 pts. 6 steps. 3 pts each.
                                return new String[][][] {
                                                { // Ex 1
                                                                { "Calcular lim(x→3) (2x - 4). Paso 1: ¿Qué hacemos?",
                                                                                "Factorizar", "Sustituir x=3",
                                                                                "Derivar", "Nada", "1", "3" },
                                                                { "Paso 2: Al sustituir, queda:", "2(3) - 4",
                                                                                "2(0) - 4", "3(2) - 4", "0", "0", "3" },
                                                                { "Paso 3: Resultado final:", "0", "1", "2", "6", "2",
                                                                                "3" }
                                                },
                                                { // Ex 2
                                                                { "Calcular lim(x→4) √x. Paso 1:", "Elevar al cuadrado",
                                                                                "Sustituir x=4", "Racionalizar", "Nada",
                                                                                "1", "3" },
                                                                { "Paso 2: Operación:", "√4", "4²", "1/4", "√2", "0",
                                                                                "3" },
                                                                { "Paso 3: Resultado:", "1", "2", "4", "16", "1", "3" }
                                                }
                                }; // Total 18 pts. Flow Total: 5+10+18 = 33.
                        case LATERALES: // Needs 18 pts.
                                return new String[][][] {
                                                { // Ex 1 (10 pts)
                                                                { "f(x)={x si x<2, 4 si x>2}. Paso 1: Lim Izquierdo (x<2):",
                                                                                "Sustituir en x", "Sustituir en 4",
                                                                                "Indeterminado", "0", "0", "3" },
                                                                { "Paso 2: Valor Lim Izquierdo:", "0", "2", "4", "∞",
                                                                                "1", "2" },
                                                                { "Paso 3: Lim Derecho (x>2):", "Sustituir en x",
                                                                                "Es constante 4", "0", "1", "1", "3" },
                                                                { "Paso 4: Conclusión:", "Límite es 2", "Límite es 4",
                                                                                "No existe", "Indefinido", "2", "2" }
                                                },
                                                { // Ex 2 (8 pts)
                                                                { "f(x) = x+1. Paso 1: Lim Izq x->1:", "0", "1", "2",
                                                                                "3", "2", "3" },
                                                                { "Paso 2: Lim Der x->1:", "0", "1", "2", "3", "2",
                                                                                "2" },
                                                                { "Paso 3: Conclusión:", "No existe", "Límite es 1",
                                                                                "Límite es 2", "Infinito", "2", "3" }
                                                }
                                }; // Total 18 pts. Flow Total: 5+10+18 = 33.

                        case PROPIEDADES: // Needs 19 pts. 6 steps.
                                // 3, 3, 3 (9) + 3, 3, 4 (10). Total 19.
                                return new String[][][] {
                                                { // Ex 1 (9 pts)
                                                                { "lim(x→2) 3x². Paso 1: Propiedad de la constante:",
                                                                                "3 * lim(x²)", "lim(3) * lim(x)",
                                                                                "Sumar 3", "Dividir 3", "0", "3" },
                                                                { "Paso 2: Evaluar lim(x²):", "2", "4", "8", "9", "1",
                                                                                "3" },
                                                                { "Paso 3: Resultado final (3 * 4):", "7", "1", "12",
                                                                                "0", "2", "3" }
                                                },
                                                { // Ex 2 (10 pts)
                                                                { "lim(x→0) (x+2)(x-2). Paso 1: Propiedad:", "Suma",
                                                                                "Producto", "Cociente", "Raíz", "1",
                                                                                "3" },
                                                                { "Paso 2: Evaluar factores:", "(0+2) y (0-2)", "0 y 0",
                                                                                "2 y 2", "1 y 1", "0", "3" },
                                                                { "Paso 3: Multiplicar 2 * (-2):", "0", "4", "-4", "1",
                                                                                "2", "4" }
                                                }
                                }; // Total 19 pts. Flow Total: 5+10+19 = 34.
                        default:
                                return new String[][][] {};
                }
        }
}

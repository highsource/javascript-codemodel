function factorial(x) {
  return x <= 1 ? 1 : x * factorial(x - 1);
}
//exercise 1: find unique element(array)
// Input: [1, 2, 2, 3, 4, 4, 5]
// Output: [1, 3, 5]
const inputData = [1, 2, 2, 3, 4, 4, 5];
export function exercise1(arr) {
  const uniqueElements = new Set(arr);
  return uniqueElements;
}
console.log(exercise1(inputData));

//exercise 2:  Check for Common Elements Between 2 Array
// Input: [1, 2, 3], [4, 5, 6]
// Output: false

// Input: [1, 2, 3], [3, 4, 5]
// Output: true

// function exercise2(arr1, arr2) {
//   let setA = new Set(arr1);
//   let isCommonElements = arr2.some((x) => setA.has(x));
//   return isCommonElements;
// }
// console.log(exercise2([1, 2, 3], [4, 5, 6]));
// console.log(exercise2([1, 2, 3], [3, 4, 5]));

//Exercise 3: Find Symmetric Difference in an Array of Arrays
// Input: [[1, 2, 3], [2, 3, 4], [3, 4, 5]]
// Output: [1, 5]  // Only 1 and 5 are elements that do not repeat in any other array

// function symmetricDifference(arrays) {
//   const seen = new Set();
//   const result = new Set();
//   const arrFlat = arrays.flat();

//   for (const item of arrFlat) {
//     console.log(item);
//     if (seen.has(item)) {
//       result.delete(item);
//     } else {
//       seen.add(item);
//       result.add(item);
//     }
//   }

//   return Array.from(result);
// }

// // Kiểm tra hàm
// const input = [
//   [1, 2, 3],
//   [2, 3, 4],
//   [3, 4, 5],
// ];
// console.log(symmetricDifference(input));


// ════════════════ Exercise 1 ════════════════════ //
/* 
TODO: Use the JSON.stringify() method to translate the value in the `object` parameter to a JSON string. Return the JSON string from the function.

@param {Object} object - an object that represents form data
@returns {string} - the JSON string representation of the 'object' param
*/
function exercise1(object) {
    return "";
}

// ════════════════ Exercise 2 ════════════════════ //
/* 
TODO: Use the JSON.parse() method to translate the value in the `jsonString` parameter to a JavaScript object. Return the object from the function.

@param {string} jsonString - a JSON string that represents form data
@returns {Object} - an Object representation of the 'jsonString' param
*/
function exercise2(jsonString) {
    return "";

}

// ════════════════════════════════════════════════ //
/* Note: You do not need to edit or view any code below this point. */

function handleSubmit(event) {
    // Prevent default form submission behavior
    event.preventDefault();

    // Retrieve form data, save as prop/value pairs in object
    const formData = new FormData(event.target);
    const dataObj = {};
    for (const pair of formData.entries()) {
        dataObj[pair[0]]= pair[1];
    }

    // get JSON string of data
    let stringResult = exercise1(dataObj);
    // format and add to JSONdiv element
    document.getElementById("JSONdiv").innerText = formatJSON(stringResult);

    // get JS Object
    let objResult = exercise2(stringResult);
    // format and add to JSdiv element
    document.getElementById("JSdiv").innerText = formatObject(objResult);

    // Reset the form
    event.target.reset();
}

// formatting helper method
function formatJSON(json) {
    // remove braces
    let noBraces = json.substring(1, json.length - 1)

    // split pairs
    let pairArray = noBraces.split(",")

    // create formatted string
    let formattedString = "{\n";

    for (let i = 0; i < pairArray.length; i++) {
        if (i < pairArray.length - 1) {
            formattedString += "\t" + pairArray[i] + ",\n";
        } else {
            formattedString += "\t" + pairArray[i] + "\n";
        }
    }

    formattedString += "}";

    return formattedString;
}

// formatting helper method
function formatObject(object) {
    return `{
            \tfood: "${object.food}",
            \tanimal: "${object.animal}",
            \tmovie: "${object.movie}",
        }`
}

// Get the form element, add event listener
document.getElementById("favoritesForm")?.addEventListener("submit", handleSubmit);


let test =exercise1(JSON.parse("{\"food\":\"potato\"}"));
console.log(test);
document.getElementById("evaluateButton").onclick = function() {
    const rule = document.getElementById("ruleInput").value;
    // Here you would send a request to your Java backend to evaluate the rule
    // For now, we will just display the rule in the result div
    document.getElementById("result").innerText = "Evaluating rule: " + rule;
};

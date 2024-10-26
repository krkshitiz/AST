# Rule Engine with Abstract Syntax Tree (AST)

## Overview
The Rule Engine application is designed to evaluate user eligibility based on dynamic rules represented by an Abstract Syntax Tree (AST). Users can create, combine, and evaluate complex conditional rules based on attributes such as age, department, income, and experience.


## Features
- Create individual rules using a string representation.
- Combine multiple rules into a single AST.
- Evaluate rules against user-provided data in JSON format.
- Error handling for invalid rule strings and data formats.
- Modify existing rules by changing operators and operand values.

## Technologies Used
- Java (Core)
- File-based storage for rules
- JSON (for data representation)

### Prerequisites
- **Java JDK**: Version 8 or higher

### Installation
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/RuleEngine.git
   cd RuleEngine

# Water Sort Puzzle Solver

This Java project solves the **Water Sort Puzzle** using classic search algorithms. The puzzle involves pouring colored liquids between bottles until each bottle contains only one color.

---

## How It Works

The agent does only one action: `pour(i, j)` to move layers from bottle *i* to *j*, following these rules:
- Bottle *i* must not be empty.
- Bottle *j* must have space.
- The top colors must match or *j* must be empty.

The goal is to find a valid sequence of actions to reach the sorted state.

---

## Search Strategies

Implemented search methods:
- **BF** – Breadth-First Search
- **DF** – Depth-First Search
- **ID** – Iterative Deepening Search
- **UC** – Uniform Cost Search
- **GR1**, **GR2** – Greedy Search with two different heuristics
- **AS1**, **AS2** – A* Search with two different admissible heuristics

The program returns: plan;pathCost;nodesExpanded Or 'NOSOLUTION' if no solution exists.

---

## Usage Example

```java
String init = "5;4;" + "b,y,r,b;" + "b,y,r,r;" + "y,r,b,y;" + "e,e,e,e;" + "e,e,e,e;";
String result = WaterSortSearch.solve(init, "AS1", true);
```

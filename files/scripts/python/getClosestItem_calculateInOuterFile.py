import sys
sys.path.append(path + 'python/')

import outerFileforCalculation

x = currentCharacter.getCords().getX()
y = currentCharacter.getCords().getY()

if currentCharacter.getItem() is not None:
    if currentCharacter.getItem().getHealth() == 0:
        singlePath = AStar.search(AStar.trashCan(), currentCharacter.getTile(), -1)
    else:
        singlePath = AStar.search(currentCharacter.getClosestKitchenEntrance(), currentCharacter.getTile(), -1)
elif currentCharacter.getItem() is None:
    singlePath = AStar.search(currentCharacter.getClosestItemTile(), currentCharacter.getTile(), -1)

if singlePath is None:
    singlePath = AStar.search(currentCharacter.getClosestUnknownTile(), currentCharacter.getTile(), -1)

if singlePath is not None:
    outerFileforCalculation.moveToClosestTile_method(currentCharacter, singlePath, x, y)
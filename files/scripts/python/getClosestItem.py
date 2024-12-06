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
    if singlePath.getLength() == 1:
        if currentCharacter.getTile().isKitchen() or currentCharacter.getTile().isTrashCan():
            currentCharacter.putDown()
        else:
            currentCharacter.pickUp()
          
    elif singlePath.getFirstTile().getCords().getX() == x and singlePath.getFirstTile().getCords().getY() - 1 == y:
        currentCharacter.moveDown()
    elif singlePath.getFirstTile().getCords().getX() == x and singlePath.getFirstTile().getCords().getY() + 1 == y:
        currentCharacter.moveUp()
    elif singlePath.getFirstTile().getCords().getX() + 1 == x and singlePath.getFirstTile().getCords().getY() == y:
        currentCharacter.moveLeft()
    elif singlePath.getFirstTile().getCords().getX() - 1 == x and singlePath.getFirstTile().getCords().getY() == y:
        currentCharacter.moveRight()
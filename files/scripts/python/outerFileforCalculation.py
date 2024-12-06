def moveToClosestTile_method(currentCharacter, singlePath, x, y):
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
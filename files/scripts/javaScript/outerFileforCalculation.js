function moveToClosestTile_method(currentCharacter) {
    if ((singlePath.getLength() == 1)) {
        if((currentCharacter.getTile().isKitchen()) || (currentCharacter.getTile().isTrashCan())){
            currentCharacter.putDown()
        } else {
            currentCharacter.pickUp()
        }
    } else if ((singlePath.getFirstTile().getCords().getX() == x) && ((singlePath.getFirstTile().getCords().getY() - 1) == y)) {
        currentCharacter.moveDown()
    } else if ((singlePath.getFirstTile().getCords().getX() == x) && ((singlePath.getFirstTile().getCords().getY() + 1) == y)) {
        currentCharacter.moveUp()
    } else if (((singlePath.getFirstTile().getCords().getX() + 1) == x) && (singlePath.getFirstTile().getCords().getY() == y)) {
        currentCharacter.moveLeft()
    } else if (((singlePath.getFirstTile().getCords().getX() - 1) == x) && (singlePath.getFirstTile().getCords().getY() == y)) {
        currentCharacter.moveRight()
    }
}
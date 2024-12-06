x = currentCharacter.getCords().getX();
y = currentCharacter.getCords().getY();
others_Aim = [];
singlePath = null;

if (isThereSavable) {
    if (currentCharacter.getClosestItemTile() != null){
        if(characterArray.length > 1) {
            if (currentCharacter.getAim() == null) {
                singlePath = AStar.search(currentCharacter.getClosestItemTile(), currentCharacter.getTile(), -1);
                for (var i = 0; i < characterArray.length; i++) {
                    if ((characterArray[i] != currentCharacter) && (characterArray[i].getAim() != null) && (singlePath != null)) {
                        if (characterArray[i].getAim() === singlePath.getLastTile()) {
                            singlePath = null;
                        }
                    }
                }
                if (currentCharacter.getClosestItemTileWithoutArray(others_Aim) != null) {
                    singlePath = AStar.search(currentCharacter.getClosestItemTileWithoutArray(others_Aim), currentCharacter.getTile(), -1);
                }
            }
        }
    }
    if (singlePath == null) {
        singlePath = AStar.search(currentCharacter.getClosestUnknownTile(), currentCharacter.getTile(), -1);
    }
    if(currentCharacter.getItem() != null) {
        if((currentCharacter.getItem().getHealth() == null) ||(currentCharacter.getItem().getHealth() > 0)){
            singlePath = AStar.search(currentCharacter.getClosestKitchenEntrance(), currentCharacter.getTile(), -1);
        } else {
            singlePath = AStar.search(AStar.trashCan(), currentCharacter.getTile(), -1);
        }
    }

    if (singlePath != null) {
        currentCharacter.setAim(singlePath.getLastTile());
         if (singlePath.getLength() == 1) {
            if (currentCharacter.getItem() != null) {
                if((currentCharacter.getTile().isKitchen()) || (currentCharacter.getTile().isTrashCan())){
                    currentCharacter.putDown();
                    currentCharacter.setAim(null);
                }
            } else {
                currentCharacter.pickUp();
            }
        }
        if (singlePath.getFirstTile() != null) {
            if ((singlePath.getFirstTile().getCords().getX() == x) && ((singlePath.getFirstTile().getCords().getY() - 1) == y)) {
                currentCharacter.moveDown();
            } else if ((singlePath.getFirstTile().getCords().getX() == x) && ((singlePath.getFirstTile().getCords().getY() + 1) == y)) {
                currentCharacter.moveUp();
            } else if (((singlePath.getFirstTile().getCords().getX() + 1) == x) && (singlePath.getFirstTile().getCords().getY() == y)) {
                currentCharacter.moveLeft();
            } else if (((singlePath.getFirstTile().getCords().getX() - 1) == x) && (singlePath.getFirstTile().getCords().getY() == y)) {
                currentCharacter.moveRight();
            }
        }
    }
}
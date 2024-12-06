x = currentCharacter.getCords().getX()
y = currentCharacter.getCords().getY()
others_Aim = []
singlePath = nil

if isThereSavable
    if  !currentCharacter.getClosestItemTile().nil?
        if characterArray.length() > 1
            if currentCharacter.getAim().nil?
                singlePath = AStar.search(currentCharacter.getClosestItemTile(), currentCharacter.getTile(), -1)
                for character in characterArray
                    if character != currentCharacter and !character.getAim().nil? and !singlePath.nil?
                        if character.getAim() == singlePath.getLastTile()
                            singlePath = nil
                        end
                    end
                end
                if !currentCharacter.getClosestItemTileWithoutArray(others_Aim).nil?
                    singlePath = AStar.search(currentCharacter.getClosestItemTileWithoutArray(others_Aim), currentCharacter.getTile(), -1)
                end
            end
        end
    end
    
    if singlePath.nil?
        singlePath = AStar.search(currentCharacter.getClosestUnknownTile(), currentCharacter.getTile(), -1)
    end
    if !currentCharacter.getItem().nil?
        if currentCharacter.getItem().getHealth().nil? or currentCharacter.getItem().getHealth() > 0
            singlePath = AStar.search(currentCharacter.getClosestKitchenEntrance(), currentCharacter.getTile(), -1)
        else
            singlePath = AStar.search(AStar.trashCan(), currentCharacter.getTile(), -1)
        end
    end

    if !singlePath.nil?
        currentCharacter.setAim(singlePath.getLastTile())
        if singlePath.getLength() == 1
            if !currentCharacter.getItem().nil?
                if currentCharacter.getTile(). isKitchen() or currentCharacter.getTile(). isTrashCan()
                    currentCharacter.putDown()
                    currentCharacter.setAim(nil)
                end
            else
                currentCharacter.pickUp()
            end
        elsif singlePath.getFirstTile().getCords().getX() == x and singlePath.getFirstTile().getCords().getY() - 1 == y
            currentCharacter.moveDown()
        elsif singlePath.getFirstTile().getCords().getX() == x and singlePath.getFirstTile().getCords().getY() + 1 == y
            currentCharacter.moveUp()
        elsif singlePath.getFirstTile().getCords().getX() + 1 == x and singlePath.getFirstTile().getCords().getY() == y
            currentCharacter.moveLeft()
        elsif singlePath.getFirstTile().getCords().getX() - 1 == x and singlePath.getFirstTile().getCords().getY() == y
            currentCharacter.moveRight()
        end
    end
end
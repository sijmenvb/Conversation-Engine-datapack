# run as the player

# check if there are no players talking to anny of the npc's in the group. if so set group to 0

# reset the bool
scoreboard players set bool CE_suc 0

# check all the NPC's
execute if entity @a[scores={lab_rat=1}] run scoreboard players set bool CE_suc 1
execute if entity @a[scores={lab_ape=1}] run scoreboard players set bool CE_suc 1

    # if no player were found close the connection
    execute if score bool CE_suc matches 0 run scoreboard players set CE_mannager CE_group_00 0
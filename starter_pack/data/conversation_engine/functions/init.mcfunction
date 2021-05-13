# detech when a player richt clicks a villager
scoreboard objectives add CE_talking minecraft.custom:minecraft.talked_to_villager
# scoreboard for storing sucsesses of functions to do conditionals
scoreboard objectives add CE_suc dummy
# scoreboard for limiting recursion
scoreboard objectives add CE_rec dummy

# groups for optimazation to be used by fake player CE_mannager
scoreboard objectives add CE_group_00 dummy

# scoreboard for villagers to be used by fake player CE_mannager
scoreboard objectives add lab_rat dummy

say conversation engine initialized
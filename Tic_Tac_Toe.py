import random
class Grid:
    matrix=[]
    ptr=-1
    def __init__(self) -> None:
        self.matrix=[" "for i in range(9)]
        self.ptr=0
    def __str__(self) -> str:
        for i in range(3):
            for j in range(3):
                print(self.matrix[self.ptr],end=" || ")
                self.ptr+=1
            print("\n--------------\n")
        self.ptr=0
        return ""
    def grid_place(self):
        flag=0
        while(flag==0):
            self.ptr=int(input("Enter box to place in "))
            if self.matrix[self.ptr-1]!=" ":
                print("Occupied")
                continue
            flag=1
        self.matrix[self.ptr-1]='X'
        temp=self.ptr
        self.ptr=0
        return temp
    def grid_place1(self,index,C):
        self.matrix[index]=C
        return
    def grid_full(self,index,char):
        self.matrix[index]=char
        return
    def comp_place(self):
        flag=0
        print("Computers turn")
        print("Placed in box:",end="")
        while(flag==0):
            rn=random.randint(1,9)
            if self.matrix[rn-1]==' ':
                self.matrix[rn-1]='O'
                flag=1
        print(rn)
        return rn
    def grid_full(self):
        if " " not in self.matrix:
            return True
        return False
    def win_check(self):
        for i in range(0,7,3):
            if self.matrix[i]==self.matrix[i+1]==self.matrix[i+2]!= " " and self.matrix[i]==self.matrix[i+1]==self.matrix[i+2]!= "F":
                self.ptr=i
                return True
        for i in range(3):
            if self.matrix[i]==self.matrix[i+3]==self.matrix[i+6]!=" " and self.matrix[i]==self.matrix[i+3]==self.matrix[i+6]!="F":
                self.ptr=i
                return True
        if self.matrix[0]==self.matrix[4]==self.matrix[8]!=" " and self.matrix[0]==self.matrix[4]==self.matrix[8]!="F":
            self.ptr=0
            return True
        if self.matrix[2]==self.matrix[4]==self.matrix[6]!=" " and self.matrix[2]==self.matrix[4]==self.matrix[6]!="F":
            self.ptr=2
            return True
        return False
def print_grid():
    for i in range(0,9,3):
        c=i
        print("|-|",end="")
        while(c<i+3):
            for j in range(0,3):
                if(j==2):
                    print(grid[c].matrix[j],end="")
                else:
                    print(grid[c].matrix[j],end="  |  ")
            print("",end="|-|")    
            c+=1
        print("\n",end='|-|')
        c=i
        while(c<i+3):
            for j in range(3,6):
                if(j==5):
                    print(grid[c].matrix[j],end="")
                else:
                    print(grid[c].matrix[j],end="  |  ")
            print("",end="|-|")
            c+=1
        print("\n",end='|-|')
        c=i
        while(c<i+3):
            for j in range(6,9):
                if(j==8):
                    print(grid[c].matrix[j],end="")
                else:
                    print(grid[c].matrix[j],end="  |  ")
            print("",end="|-|")
            c+=1
        print("\n---------------------------------------------------")
def status(grid):
    if(grid.win_check()):
        if(grid.matrix[grid.ptr]=='X'):
            return 'X'
        else:
            return 'O'
    elif(grid.grid_full()):
        return 'F'
    else:
        return ' '
    


grid=[]
for i in range(9):
    o=Grid()
    grid.append(o)
    o=None
Result=Grid()
index,flag=(1,1)

while(True):
    print_grid()
    if(Result.win_check()):
        print(Result.matrix[Result.ptr],"WINS")
        break
    if(Result.grid_full()):
        print("Its a tie")
        break    

    if(flag==1):
        index=int(input("Enter which board u want to play in"))
        if(Result.matrix[index-1]!=" "):
            continue
        ind=grid[index-1].grid_place()
        Result.grid_place1(index-1,status(grid[index-1]))
        if(Result.matrix[index-1]=='X'):
            print("U WON THIS BOX")
        index=ind
        Result.grid_place1(index-1,status(grid[index-1]))
        if(Result.matrix[index-1]!=" "):
            flag=2
        else:
            flag=0
        continue
    
    if(flag==2):
        print("Computer chose board ",end=":")
        while(True):
            index=random.randint(1,9)
            if(Result.matrix[index-1]==" "):
                break
        print(index)
        ind=grid[index-1].comp_place()
        Result.grid_place1(index-1,status(grid[index-1]))
        if(Result.matrix[index-1]=="O"):
            print("COMPUTER WON THIS BOX")
        index=ind
        Result.grid_place1(index-1,status(grid[index-1]))
        if(Result.matrix[index-1]!=" "):
            flag=1
        else:
            flag=-1
        continue
    if(flag<=0):
        print("Playing in Board",index)
        if(flag==-1):
            ind=grid[index-1].grid_place()
            Result.grid_place1(index-1,status(grid[index-1]))
            if(Result.matrix[index-1]=='X'):
                print("U WON THIS BOX")
            index=ind
            Result.grid_place1(index-1,status(grid[index-1]))
            if(Result.matrix[index-1]!=" "):
                flag=2
            else:
                flag=0
            continue
        if(flag==0):
            ind=grid[index-1].comp_place()
            Result.grid_place1(index-1,status(grid[index-1]))
            if(Result.matrix[index-1]=='O'):
                print("COMPUTER WON THIS BOX")
            index=ind
            Result.grid_place1(index-1,status(grid[index-1]))
            if(Result.matrix[index-1]!=" "):
                flag=1
            else:
                flag=-1
            continue





print_grid()